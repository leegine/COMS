head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������}�X�^�N���X(WEB3AccInfoMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/22 ������ (���u) �V�K�쐬
                   2006/10/9  ꎉ�   (���u) ���f��No.124
                   2006/10/30 ꎉ�   (���u) ���f��No.139
*/
package webbroker3.accountinfo;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������}�X�^�N���X)<BR>
 * �������}�X�^�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AccInfoMaster implements BusinessObject
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMaster.class);
    
    /**
     * (�������}�X�^�s�I�u�W�F�N�g)<BR>
     * �������}�X�^�s�I�u�W�F�N�g <BR>
     * <BR>
     * �� �������}�X�^Params�N���X��DDL��莩����������B<BR>
     */
    private AccountInfoMstParams accountInfoMstParams;
    
    /**
     *�igetDataSourceObject�̎���)<BR>
     * getDataSourceObject�̎��� <BR>
     * <BR>
     * this.�������}�X�^�s��ԋp����B <BR>
     */
    public Object getDataSourceObject()
    {
        return this.accountInfoMstParams;
    }
    
    /**
     * (�������}�X�^)
     *�R���X�g���N�^�B
     */
    public WEB3AccInfoMaster(AccountInfoMstParams l_accountInfoMstParams) 
    {
        this.accountInfoMstParams = l_accountInfoMstParams;
    }
    /**
     * �istatic ���\�b�h)<BR>
     * �������}�X�^�V�K�s�𐶐�����B<BR>
     * <BR><BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
     * �@@�������}�X�^Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@���������}�X�^Params��DDL��莩����������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B <BR>
     * �@@�P�j�Ő��������������}�X�^Params�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B<BR> 
     * <BR>
     * �@@�������}�X�^Params.�����h�c = �ڋq.getAccountId()<BR>
     * �@@�������}�X�^Params.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@�������}�X�^Params.���X�R�[�h = �ڋq.getBranch().getBranchId()<BR>
     * �@@�������}�X�^Params.�����R�[�h = �ڋq.getAccountCode()<BR>
     * �@@�������}�X�^Params.�쐬���� = TradingSystem.getSystemTimestamp() <BR>
     * �@@�������}�X�^Params.�X�V���� = TradingSystem.getSystemTimestamp() <BR>
     * �@@�������}�X�^Params.�X�V�҃R�[�h = �X�V�҃R�[�h<BR>
     * �@@�������}�X�^Params.�ڋq���i�@@�l���j�J�i = �ڋq.���O�i�c��1�j<BR>
     * �@@�������}�X�^Params.�ڋq�������̂P = �g�єԍ��E�Ζ�����.�������̂P<BR>
     * �@@�������}�X�^Params.�ڋq�������̂Q = �g�єԍ��E�Ζ�����.�������̂Q<BR>
     * �@@�������}�X�^Params.�E�Ƌ敪 = �g�єԍ��E�Ζ�����.�E��<BR>
     * �@@�������}�X�^Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��<BR>
     * �@@�������}�X�^Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��<BR>
     * �@@�������}�X�^Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��<BR>
     * �@@�������}�X�^Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��<BR>
     * �@@�������}�X�^Params.��\�Ҍ�= �g�єԍ��E�Ζ�����.��\�Ҍ�<BR>
     * �@@�������}�X�^Params.����ӔC�Җ��i�����j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��<BR>
     * �@@�������}�X�^Params.����ӔC�Җ��i�����j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��<BR>
     * �@@�������}�X�^Params.����ӔC�Җ��i�J�i�j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��<BR>
     * �@@�������}�X�^Params.����ӔC�Җ��i�J�i�j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��<BR>
     * �@@�������}�X�^Params.����ӔC�ҁ@@��������= �g�єԍ��E�Ζ�����.����ӔC�ҏ�������<BR>
     * �@@�������}�X�^Params.����ӔC�ҁ@@��E= �g�єԍ��E�Ζ�����.����ӔC�Җ�E<BR>
     * �@@�������}�X�^Params.����ӔC�җX�֔ԍ�= �g�єԍ��E�Ζ�����.�����ӔC�җX�֔ԍ�<BR>
     * �@@�������}�X�^Params.����ӔC�ҏZ���P= �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���P<BR>
     * �@@�������}�X�^Params.����ӔC�ҏZ���Q= �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���Q<BR>
     * �@@�������}�X�^Params.����ӔC�ҏZ���R= �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���R<BR>
     * �@@�������}�X�^Params.����ӔC�Ґ��N�����@@�N��= �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N�����N��<BR>
     * �@@�������}�X�^Params.����ӔC�Ґ��N����= �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N����<BR>
     * �@@�������}�X�^Params.����ӔC�҉�В��ʔԍ�= �g�єԍ��E�Ζ�����.�����ӔC�҉�В��ʔԍ�<BR>
     * �@@�������}�X�^Params.���̑��A����i�g�сA����j= �g�єԍ��E�Ζ�����.���̑��̘A����i�g�сA����j<BR>
     * �@@�������}�X�^Params.�Ζ��於�� = null<BR>
     * �@@�������}�X�^Params.�Ζ���X�֔ԍ� = null<BR>
     * �@@�������}�X�^Params.�Ζ���Z�� = null<BR>
     * �@@�������}�X�^Params.���� = �g�єԍ��E�Ζ�����.���� <BR>
     * �@@�������}�X�^Params.��E = null<BR>
     * �@@�������}�X�^Params.�Ζ���d�b�ԍ� = null<BR>
     * �@@�������}�X�^Params.�A����d�b�ԍ��i�g�сj= null<BR>
     * �@@�������}�X�^Params.�A����D�揇�� 1�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 1��<BR>
     * �@@�������}�X�^Params.�A����D�揇�� 2�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 2��<BR>
     * �@@�������}�X�^Params.�A����D�揇�� 3�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 3��<BR>
     * �@@�������}�X�^Params.���� = �g�єԍ��E�Ζ�����.����<BR>
     * �@@�������}�X�^Params.���Ђ��̑� = �g�єԍ��E�Ζ�����.���Ђ��̑�<BR>
     * �@@�������}�X�^Params.FAX�ԍ� = �g�єԍ��E�Ζ�����.FAX�ԍ� <BR>
     * �@@�������}�X�^Params.�N�� = �g�єԍ��E�Ζ�����.�N�� <BR>
�@@   * �@@�������}�X�^Params.���Z���Y�z = �g�єԍ��E�Ζ�����.���Z���Y�z <BR>
�@@   * �@@�������}�X�^Params.�^�p�\��z = �g�єԍ��E�Ζ�����.�^�p�\��z <BR>
�@@   * �@@�������}�X�^Params.�����ړI = �g�єԍ��E�Ζ�����.�����ړI <BR>
�@@   * �@@�������}�X�^Params.�����\����� = �g�єԍ��E�Ζ�����.�����\����� <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�P�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�Q�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�R�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�R�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�S�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�S�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�T�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�T�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�U�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�U�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�V�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�V�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�W�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�W�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�X�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�X�j <BR>
�@@   * �@@�������}�X�^Params.�����o���̗L���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�P�j = �g�єԍ��E�Ζ�����.�����o���i�P�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�Q�j = �g�єԍ��E�Ζ�����.�����o���i�Q�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�R�j = �g�єԍ��E�Ζ�����.�����o���i�R�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�S�j = �g�єԍ��E�Ζ�����.�����o���i�S�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�T�j = �g�єԍ��E�Ζ�����.�����o���i�T�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�U�j = �g�єԍ��E�Ζ�����.�����o���i�U�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�V�j = �g�єԍ��E�Ζ�����.�����o���i�V�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�W�j = �g�єԍ��E�Ζ�����.�����o���i�W�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�X�j = �g�єԍ��E�Ζ�����.�����o���i�X�j <BR>
�@@   * �@@�������}�X�^Params.�����o���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���i�P�O�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�P�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�Q�j = �g�єԍ��E�Ζ�����.����̎�ށi�Q�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�R�j = �g�єԍ��E�Ζ�����.����̎�ށi�R�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�S�j = �g�єԍ��E�Ζ�����.����̎�ށi�S�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�T�j = �g�єԍ��E�Ζ�����.����̎�ށi�T�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�U�j = �g�єԍ��E�Ζ�����.����̎�ށi�U�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�V�j = �g�єԍ��E�Ζ�����.����̎�ށi�V�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�W�j = �g�єԍ��E�Ζ�����.����̎�ށi�W�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�X�j = �g�єԍ��E�Ζ�����.����̎�ށi�X�j <BR>
�@@   * �@@�������}�X�^Params.����̎�ށi�P�O�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�O�j <BR>
�@@   * �@@�������}�X�^Params.�����J�݂̓��@@ = �g�єԍ��E�Ζ�����.�����J�݂̓��@@ <BR>
�@@   * �@@�������}�X�^Params.�����J�݂̓��@@�̏ڍ� = �g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� <BR>
�@@   * �@@�������}�X�^Params.���ݗ��p���Ă���،���� = �g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� <BR>
�@@   * �@@�������}�X�^Params.�C���^�[�l�b�g����敪 = �g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 <BR>
�@@   * �@@�������}�X�^Params.�Љ�x�X = �g�єԍ��E�Ζ�����.�Љ�x�X <BR>
     * <BR>
     * �R�j�@@�������}�X�^�I�u�W�F�N�g�ԋp<BR>
     * �@@�s�I�u�W�F�N�g���w�肵�A�������}�X�^�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * @@param l_mainAccount - �ڋq
     * @@param l_accInfoMobileOfficeInfo - �g�єԍ��E�Ζ�����
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@return AccountInfoMstParams
     */
    public static WEB3AccInfoMaster createNewAccInfoMaster (
        MainAccount l_mainAccount, 
        WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo, 
        String l_strUpdaterCode)
    {
        final String STR_METHOD_NAME = " createNewAccInfoMaster(MainAccount, WEB3AccInfoMobileOfficeInfo, String) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�s�I�u�W�F�N�g����
        // �@@�������}�X�^Params�I�u�W�F�N�g�𐶐�����B
        AccountInfoMstParams l_accInfoMstParams = new AccountInfoMstParams();
        
        //�Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
        //�P�j�Ő��������������}�X�^Params�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B
        //�������}�X�^Params.�����h�c = �ڋq.getAccountId()
        l_accInfoMstParams.setAccountId(l_mainAccount.getAccountId());
        
        //�������}�X�^Params.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
        l_accInfoMstParams.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        
        //�������}�X�^Params.���X�R�[�h = �ڋq.getBranch().getBranchId()
        l_accInfoMstParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        
        //�������}�X�^Params.�����R�[�h = �ڋq.getAccountCode()
        l_accInfoMstParams.setAccountCode(l_mainAccount.getAccountCode());
        
        //�������}�X�^Params.�쐬���� = TradingSystem.getSystemTimestamp()
        l_accInfoMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�������}�X�^Params.�X�V���� = TradingSystem.getSystemTimestamp()
        l_accInfoMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�������}�X�^Params.�X�V�҃R�[�h = �X�V�҃R�[�h
        l_accInfoMstParams.setLastUpdater(l_strUpdaterCode);
        
        //�������}�X�^Params.�ڋq���i�@@�l���j�J�i = �ڋq.���O�i�c��1�j
        MainAccountRow l_row = (MainAccountRow)l_mainAccount.getDataSourceObject();
        l_accInfoMstParams.setFamilyNameAlt1(l_row.getFamilyNameAlt1());
        
        //�������}�X�^Params.�ڋq�������̂P = �g�єԍ��E�Ζ�����.�������̂P 
        l_accInfoMstParams.setRealName1(l_accInfoMobileOfficeInfo.accountRealName1);
        
        //�������}�X�^Params.�ڋq�������̂Q = �g�єԍ��E�Ζ�����.�������̂Q
        l_accInfoMstParams.setRealName2(l_accInfoMobileOfficeInfo.accountRealName2);
        
        //�������}�X�^Params.�E�Ƌ敪 = �g�єԍ��E�Ζ�����.�E��
        l_accInfoMstParams.setOccupationDiv(l_accInfoMobileOfficeInfo.occupationDiv);
        
        //�������}�X�^Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��
        l_accInfoMstParams.setRepresentFamilyName(l_accInfoMobileOfficeInfo.representFamilyName);
        
        //�������}�X�^Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��
        l_accInfoMstParams.setRepresentGivenName(l_accInfoMobileOfficeInfo.representName);
        
        //�������}�X�^Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��
        l_accInfoMstParams.setRepresentFamilyNameAlt1(l_accInfoMobileOfficeInfo.representFamilyNameKana);
        
        //�������}�X�^Params.��\�Җ��i�J�i�j�� =  �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��
        l_accInfoMstParams.setRepresentGivenNameAlt1(l_accInfoMobileOfficeInfo.representNameKana);
        
        //�������}�X�^Params.��\�Ҍ�= �g�єԍ��E�Ζ�����.��\�Ҍ�
        l_accInfoMstParams.setRepresentPower(l_accInfoMobileOfficeInfo.representPower);
        
        //�������}�X�^Params.����ӔC�Җ��i�����j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��
        l_accInfoMstParams.setDirectorFamilyName(l_accInfoMobileOfficeInfo.directorFamilyName);
        
        //�������}�X�^Params.����ӔC�Җ��i�����j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��
        l_accInfoMstParams.setDirectorGivenName(l_accInfoMobileOfficeInfo.directorName);
        
        //�������}�X�^Params.����ӔC�Җ��i�J�i�j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��
        l_accInfoMstParams.setDirectorFamilyNameAlt1(l_accInfoMobileOfficeInfo.directorFamilyNameKana);
        
        //�������}�X�^Params.����ӔC�Җ��i�J�i�j��= �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��
        l_accInfoMstParams.setDirectorGivenNameAlt1(l_accInfoMobileOfficeInfo.directorNameKana);
        
        //�������}�X�^Params.����ӔC�ҁ@@��������= �g�єԍ��E�Ζ�����.����ӔC�ҏ�������
        l_accInfoMstParams.setDirectorDepartment(l_accInfoMobileOfficeInfo.directorDepartment);
        
        //�������}�X�^Params.����ӔC�ҁ@@��E= �g�єԍ��E�Ζ�����.����ӔC�Җ�E 
        l_accInfoMstParams.setDirectorPost(l_accInfoMobileOfficeInfo.directorPosition);
        
        //�������}�X�^Params.����ӔC�җX�֔ԍ�= �g�єԍ��E�Ζ�����.�����ӔC�җX�֔ԍ�
        l_accInfoMstParams.setDirectorZipCode(l_accInfoMobileOfficeInfo.directorZipCode);
        
        //�������}�X�^Params.����ӔC�ҏZ���P= �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���P 
        l_accInfoMstParams.setDirectorAddress1(l_accInfoMobileOfficeInfo.directorAddress1);
        
        //�������}�X�^Params.����ӔC�ҏZ���Q= �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���Q 
        l_accInfoMstParams.setDirectorAddress2(l_accInfoMobileOfficeInfo.directorAddress2);
        
        //�������}�X�^Params.����ӔC�ҏZ���R= �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���R
        l_accInfoMstParams.setDirectorAddress3(l_accInfoMobileOfficeInfo.directorAddress3);
        
        //�������}�X�^Params.����ӔC�Ґ��N�����@@�N��= �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N�����N��
        l_accInfoMstParams.setDirectorEraBorn(l_accInfoMobileOfficeInfo.directorEraBorn);
        
        //�������}�X�^Params.����ӔC�Ґ��N����= �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N����
        l_accInfoMstParams.setDirectorBornDate(l_accInfoMobileOfficeInfo.directorBornDate);
        
        //�������}�X�^Params.����ӔC�҉�В��ʔԍ�= �g�єԍ��E�Ζ�����.�����ӔC�҉�В��ʔԍ�
        l_accInfoMstParams.setDirectorCorpTelephone(l_accInfoMobileOfficeInfo.directorCorpDirect);
        
        //�������}�X�^Params.���̑��A����i�g�сA����j= �g�єԍ��E�Ζ�����.���̑��̘A����i�g�сA����j
        l_accInfoMstParams.setOtherContact(l_accInfoMobileOfficeInfo.directorOtherContact);
        
        //�������}�X�^Params.�Ζ��於�� = null
        l_accInfoMstParams.setOffice(null);
        
        //�������}�X�^Params.�Ζ���X�֔ԍ� = null
        l_accInfoMstParams.setOfficeZipCode(null);
        
        //�������}�X�^Params.�Ζ���Z�� = null
        l_accInfoMstParams.setOfficeAddress(null);
        
        //�������}�X�^Params.���� = �g�єԍ��E�Ζ�����.���� 
        l_accInfoMstParams.setDepartment(l_accInfoMobileOfficeInfo.department);
        
        //�������}�X�^Params.��E = null
        l_accInfoMstParams.setPost(null);
        
        //�������}�X�^Params.�Ζ���d�b�ԍ� = null
        l_accInfoMstParams.setOfficeTelephone(null);
        
        //�������}�X�^Params.�A����d�b�ԍ��i�g�сj= null
        l_accInfoMstParams.setMobile(null);
        
        //�������}�X�^Params.�A����D�揇�� 1�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 1��
        l_accInfoMstParams.setContactPriority1(l_accInfoMobileOfficeInfo.contactPriority1);
        
        //�������}�X�^Params.�A����D�揇�� 2�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 2��
        l_accInfoMstParams.setContactPriority2(l_accInfoMobileOfficeInfo.contactPriority2);
        
        //�������}�X�^Params.�A����D�揇�� 3�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 3��
        l_accInfoMstParams.setContactPriority3(l_accInfoMobileOfficeInfo.contactPriority3);
        
        //�������}�X�^Params.���� = �g�єԍ��E�Ζ�����.����
        l_accInfoMstParams.setNationality(l_accInfoMobileOfficeInfo.nationality);
        
        //�������}�X�^Params.���Ђ��̑� = �g�єԍ��E�Ζ�����.���Ђ��̑� 
        l_accInfoMstParams.setNationalityOther(l_accInfoMobileOfficeInfo.nationalityOther);
        
		//   �@@�������}�X�^Params.FAX�ԍ� = �g�єԍ��E�Ζ�����.FAX�ԍ� 
        l_accInfoMstParams.setFax(l_accInfoMobileOfficeInfo.faxTelephone);
        
		//   �@@�������}�X�^Params.�N�� = �g�єԍ��E�Ζ�����.�N�� 
        l_accInfoMstParams.setAnnualIncomeDiv(l_accInfoMobileOfficeInfo.annualIncomeDiv);
        
		//   �@@�������}�X�^Params.���Z���Y�z = �g�єԍ��E�Ζ�����.���Z���Y�z 
        l_accInfoMstParams.setAssetValueDiv(l_accInfoMobileOfficeInfo.assetValueDiv);
        
		//   �@@�������}�X�^Params.�^�p�\��z = �g�єԍ��E�Ζ�����.�^�p�\��z 
        l_accInfoMstParams.setFundBudgetAmountDiv(l_accInfoMobileOfficeInfo.fundBundgetAmountDiv);
        
		//   �@@�������}�X�^Params.�����ړI = �g�єԍ��E�Ζ�����.�����ړI 
        l_accInfoMstParams.setInvestPurposeDiv(l_accInfoMobileOfficeInfo.investPurposeDiv);
        
		//   �@@�������}�X�^Params.�����\����� = �g�єԍ��E�Ζ�����.�����\����� 
        l_accInfoMstParams.setInvestPlanPeriodDiv(l_accInfoMobileOfficeInfo.investPlanPeriodDiv);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�P�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�j 
        Integer l_intExperienceFlag1 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag1 != null)
        {
            l_intExperienceFlag1 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag1);
        }
        l_accInfoMstParams.setExperienceFlag1(l_intExperienceFlag1);
        
        
		//   �@@�������}�X�^Params.�����o���̗L���i�Q�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j
        Integer l_intExperienceFlag2 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag2 != null)
        {
            l_intExperienceFlag2 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag2);
        }
        l_accInfoMstParams.setExperienceFlag2(l_intExperienceFlag2);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�R�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�R�j
        Integer l_intExperienceFlag3 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag3 != null)
        {
            l_intExperienceFlag3 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag3);
        }
        l_accInfoMstParams.setExperienceFlag3(l_intExperienceFlag3);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�S�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�S�j
        Integer l_intExperienceFlag4 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag4 != null)
        {
            l_intExperienceFlag4 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag4);
        }
        l_accInfoMstParams.setExperienceFlag4(l_intExperienceFlag4);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�T�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�T�j 
        Integer l_intExperienceFlag5 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag5 != null)
        {
            l_intExperienceFlag5 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag5);
        }
        l_accInfoMstParams.setExperienceFlag5(l_intExperienceFlag5);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�U�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�U�j 
        Integer l_intExperienceFlag6 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag6 != null)
        {
            l_intExperienceFlag6 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag6);
        }
        l_accInfoMstParams.setExperienceFlag6(l_intExperienceFlag6);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�V�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�V�j
        Integer l_intExperienceFlag7 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag7 != null)
        {
            l_intExperienceFlag7 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag7);
        }
        l_accInfoMstParams.setExperienceFlag7(l_intExperienceFlag7);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�W�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�W�j
        Integer l_intExperienceFlag8 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag8 != null)
        {
            l_intExperienceFlag8 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag8);
        }
        l_accInfoMstParams.setExperienceFlag8(l_intExperienceFlag8);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�X�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�X�j 
        Integer l_intExperienceFlag9 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag9 != null)
        {
            l_intExperienceFlag9 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag9);
        }
        l_accInfoMstParams.setExperienceFlag9(l_intExperienceFlag9);
        
		//   �@@�������}�X�^Params.�����o���̗L���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j
        Integer l_intExperienceFlag10 = null;
        if (l_accInfoMobileOfficeInfo.experienceFlag10 != null)
        {
            l_intExperienceFlag10 = new Integer(l_accInfoMobileOfficeInfo.experienceFlag10);
        }
        l_accInfoMstParams.setExperienceFlag10(l_intExperienceFlag10);
        
		//   �@@�������}�X�^Params.�����o���i�P�j = �g�єԍ��E�Ζ�����.�����o���i�P�j 
        l_accInfoMstParams.setExperienceDiv1(l_accInfoMobileOfficeInfo.experienceDiv1);
        
		//   �@@�������}�X�^Params.�����o���i�Q�j = �g�єԍ��E�Ζ�����.�����o���i�Q�j
        l_accInfoMstParams.setExperienceDiv2(l_accInfoMobileOfficeInfo.experienceDiv2);
        
		//   �@@�������}�X�^Params.�����o���i�R�j = �g�єԍ��E�Ζ�����.�����o���i�R�j
        l_accInfoMstParams.setExperienceDiv3(l_accInfoMobileOfficeInfo.experienceDiv3);
        
		//   �@@�������}�X�^Params.�����o���i�S�j = �g�єԍ��E�Ζ�����.�����o���i�S�j
        l_accInfoMstParams.setExperienceDiv4(l_accInfoMobileOfficeInfo.experienceDiv4);
        
		//   �@@�������}�X�^Params.�����o���i�T�j = �g�єԍ��E�Ζ�����.�����o���i�T�j 
        l_accInfoMstParams.setExperienceDiv5(l_accInfoMobileOfficeInfo.experienceDiv5);
        
		//   �@@�������}�X�^Params.�����o���i�U�j = �g�єԍ��E�Ζ�����.�����o���i�U�j
        l_accInfoMstParams.setExperienceDiv6(l_accInfoMobileOfficeInfo.experienceDiv6);
        
		//   �@@�������}�X�^Params.�����o���i�V�j = �g�єԍ��E�Ζ�����.�����o���i�V�j 
        l_accInfoMstParams.setExperienceDiv7(l_accInfoMobileOfficeInfo.experienceDiv7);
        
		//   �@@�������}�X�^Params.�����o���i�W�j = �g�єԍ��E�Ζ�����.�����o���i�W�j
        l_accInfoMstParams.setExperienceDiv8(l_accInfoMobileOfficeInfo.experienceDiv8);
        
		//   �@@�������}�X�^Params.�����o���i�X�j = �g�єԍ��E�Ζ�����.�����o���i�X�j
        l_accInfoMstParams.setExperienceDiv9(l_accInfoMobileOfficeInfo.experienceDiv9);
        
		//   �@@�������}�X�^Params.�����o���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���i�P�O�j 
        l_accInfoMstParams.setExperienceDiv10(l_accInfoMobileOfficeInfo.experienceDiv10);
        
		//   �@@�������}�X�^Params.����̎�ށi�P�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�j
        Integer l_intInterest1 = null;
        if (l_accInfoMobileOfficeInfo.interest1 != null)
        {
            l_intInterest1 = new Integer(l_accInfoMobileOfficeInfo.interest1);
        }
        l_accInfoMstParams.setInterestFlag1(l_intInterest1);
        
		//   �@@�������}�X�^Params.����̎�ށi�Q�j = �g�єԍ��E�Ζ�����.����̎�ށi�Q�j
        Integer l_intInterest2 = null;
        if (l_accInfoMobileOfficeInfo.interest2 != null)
        {
            l_intInterest2 = new Integer(l_accInfoMobileOfficeInfo.interest2);
        }
        l_accInfoMstParams.setInterestFlag2(l_intInterest2);
        
        
		//   �@@�������}�X�^Params.����̎�ށi�R�j = �g�єԍ��E�Ζ�����.����̎�ށi�R�j
        Integer l_intInterest3 = null;
        if (l_accInfoMobileOfficeInfo.interest3 != null)
        {
            l_intInterest3 = new Integer(l_accInfoMobileOfficeInfo.interest3);
        }
        l_accInfoMstParams.setInterestFlag3(l_intInterest3);
        
        
		//   �@@�������}�X�^Params.����̎�ށi�S�j = �g�єԍ��E�Ζ�����.����̎�ށi�S�j
        Integer l_intInterest4 = null;
        if (l_accInfoMobileOfficeInfo.interest4 != null)
        {
            l_intInterest4 = new Integer(l_accInfoMobileOfficeInfo.interest4);
        }
        l_accInfoMstParams.setInterestFlag4(l_intInterest4);
        
		//   �@@�������}�X�^Params.����̎�ށi�T�j = �g�єԍ��E�Ζ�����.����̎�ށi�T�j
        Integer l_intInterest5 = null;
        if (l_accInfoMobileOfficeInfo.interest5 != null)
        {
            l_intInterest5 = new Integer(l_accInfoMobileOfficeInfo.interest5);
        }
        l_accInfoMstParams.setInterestFlag5(l_intInterest5);
        
		//   �@@�������}�X�^Params.����̎�ށi�U�j = �g�єԍ��E�Ζ�����.����̎�ށi�U�j 
        Integer l_intInterest6 = null;
        if (l_accInfoMobileOfficeInfo.interest6 != null)
        {
            l_intInterest6 = new Integer(l_accInfoMobileOfficeInfo.interest6);
        }
        l_accInfoMstParams.setInterestFlag6(l_intInterest6);
        
		//   �@@�������}�X�^Params.����̎�ށi�V�j = �g�єԍ��E�Ζ�����.����̎�ށi�V�j 
        Integer l_intInterest7 = null;
        if (l_accInfoMobileOfficeInfo.interest7 != null)
        {
            l_intInterest7 = new Integer(l_accInfoMobileOfficeInfo.interest7);
        }
        l_accInfoMstParams.setInterestFlag7(l_intInterest7);
        
		//   �@@�������}�X�^Params.����̎�ށi�W�j = �g�єԍ��E�Ζ�����.����̎�ށi�W�j
        Integer l_intInterest8 = null;
        if (l_accInfoMobileOfficeInfo.interest8 != null)
        {
            l_intInterest8 = new Integer(l_accInfoMobileOfficeInfo.interest8);
        }
        l_accInfoMstParams.setInterestFlag8(l_intInterest8);
        
		//   �@@�������}�X�^Params.����̎�ށi�X�j = �g�єԍ��E�Ζ�����.����̎�ށi�X�j 
        Integer l_intInterest9 = null;
        if (l_accInfoMobileOfficeInfo.interest9 != null)
        {
            l_intInterest9 = new Integer(l_accInfoMobileOfficeInfo.interest9);
        }
        l_accInfoMstParams.setInterestFlag9(l_intInterest9);
        
		//   �@@�������}�X�^Params.����̎�ށi�P�O�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�O�j 
        Integer l_intInterest10 = null;
        if (l_accInfoMobileOfficeInfo.interest10 != null)
        {
            l_intInterest10 = new Integer(l_accInfoMobileOfficeInfo.interest10);
        }
        l_accInfoMstParams.setInterestFlag10(l_intInterest10);
        
		//   �@@�������}�X�^Params.�����J�݂̓��@@ = �g�єԍ��E�Ζ�����.�����J�݂̓��@@ 
        l_accInfoMstParams.setAppliMotivatDiv(l_accInfoMobileOfficeInfo.appliMotivatDiv);
        
		//   �@@�������}�X�^Params.�����J�݂̓��@@�̏ڍ� = �g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� 
        l_accInfoMstParams.setAppliMotivatDivDetail(l_accInfoMobileOfficeInfo.appliMotivatDetail);
        
		//   �@@�������}�X�^Params.���ݗ��p���Ă���،���� = �g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� 
        l_accInfoMstParams.setUseInstitutionDiv(l_accInfoMobileOfficeInfo.useInstitutionDiv);
        
		//   �@@�������}�X�^Params.�C���^�[�l�b�g����敪 = �g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 
        l_accInfoMstParams.setInternetTradeDiv(l_accInfoMobileOfficeInfo.internetTradeDiv);
        
		//   �@@�������}�X�^Params.�Љ�x�X = �g�єԍ��E�Ζ�����.�Љ�x�X 
        l_accInfoMstParams.setIntroduceBranchCode(l_accInfoMobileOfficeInfo.introduceBranch);
        
        //�R�j�@@�������}�X�^�I�u�W�F�N�g�ԋp
        // �@@�s�I�u�W�F�N�g���w�肵�A�������}�X�^�I�u�W�F�N�g�𐶐����ԋp����B
        log.exiting(STR_METHOD_NAME);
        return new WEB3AccInfoMaster(l_accInfoMstParams);
    }
    
    /**
     *�istatic ���\�b�h�j<BR>
     *�ڋq�ɊY������������}�X�^���擾����B<BR>
     *<BR>
     *�P�j�@@�������}�X�^�e�[�u������<BR>
     *�@@�ȉ��̏����ŁA�������}�X�^�e�[�u������������B<BR>
     *<BR>
     *�@@[����]<BR>
     *�@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And<BR>
     *  ���X�R�[�h= �ڋq.getBranch().getBranchCode()  And <BR>
     *  �����R�[�h = �ڋq.getAccountCode() <BR>
     *<BR>
     *�Q�j�@@�������}�X�^�I�u�W�F�N�g�ԋp <BR>
     *�@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA�������}�X�^�I�u�W�F�N�g�𐶐����ԋp����B<BR> 
     *�@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B <BR>
     *�@@�s���������擾�ł����ꍇ�́A�f�[�^�s�����Ɣ��肵�A��O���X���[����B<BR> 
     * @@param l_mainAccount - �ڋq
     * @@return AccountInfoMstParams
     */
    public static WEB3AccInfoMaster getAccInfoMaster(MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccInfoMaster(MainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�������}�X�^�e�[�u������
        //�@@�ȉ��̏����ŁA�������}�X�^�e�[�u������������
        
        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strQueryString = "institution_code = ? and branch_code = ? and account_code = ?";
            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode()
                };

            //�ȉ��̏����ŁA�������}�X�^�e�[�u������������B
            //[����]
            //�@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
            //  ���X�R�[�h= �ڋq.getBranch().getBranchCode()  And 
            //  �����R�[�h = �ڋq.getAccountCode() 
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                AccountInfoMstRow.TYPE,
                l_strQueryString,
                l_queryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�Q�j�@@�������}�X�^�I�u�W�F�N�g�ԋp 
        //�擾�����e�s�I�u�W�F�N�g�ɂ��āA�������}�X�^�I�u�W�F�N�g�𐶐����ԋp����B
        else if (l_lisRecords.size() == 1)
        {
            AccountInfoMstRow l_row = (AccountInfoMstRow)l_lisRecords.get(0);
            
            log.exiting(STR_METHOD_NAME);
            return new WEB3AccInfoMaster(new AccountInfoMstParams(l_row));
        }
        //�s���������擾�ł����ꍇ�́A�f�[�^�s�����Ɣ��肵�A��O���X���[����B
        else
        {
            log.debug("�f�[�^�s�����G���[�B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME);
        }
    }
}
@
