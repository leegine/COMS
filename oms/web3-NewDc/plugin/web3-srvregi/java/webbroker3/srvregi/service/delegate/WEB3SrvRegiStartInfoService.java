head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.50.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStartInfoService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�N�����T�[�r�X(WEB3SrvRegiStartInfoService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 ���o�� �V�K�쐬
Revesion History : 2005/10/05 ��؁@@���R�I(SRA) �g�����X�����N�Ή�
Revesion History : 2005/10/18 ��؁@@���R�I(SRA) �t�B�f���e�B�Ή�
Revesion History : 2005/10/18 �s�p�@@(���u) �d�l�ύX���f��No.231�Ή�
Revesion History : 2009/04/28 �Ԑi  (���u) �d�l�ύX���f��No.415
Revesion History : 2009/05/20 �đo�g(���u) �d�l�ύX���f��No.419�Ή�
*/

package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.login.service.delegate.WEB3DigestKey;

/**
 * (�T�[�r�X���p�N�����T�[�r�X) <BR>
 * �T�[�r�X���p�N�����T�[�r�X�C���^�[�t�F�C�X <BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public interface WEB3SrvRegiStartInfoService extends Service
{

    /**
     * (get�Í����ڋq�R�[�h) <BR>
     * �i�I���b�N�X�،� �P���~���j�A���ɂĎg�p�j <BR>
     * <BR>
     * ����.�ڋq�R�[�h�ɈÍ������{���A���̌��ʂ�ԋp����B <BR>
     * 
     * @@param l_strMainAccountCode -
     *            (�ڋq�R�[�h)
     * @@return String
     * @@roseuid 41B8FB2F0329
     */
    public String getCryptAccountCode(String l_strMainAccountCode);

    /**
     * (create�n�b�V���l) <BR>
     * ��������w�肳�ꂽ���x���_�[�T�[�r�X�̃n�b�V���l�����߂�B <BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jcreate�n�b�V���l�v�Q�� <BR>
     * 
     * @@param l_strInstitutionCode -
     *            (�،���ЃR�[�h) <BR>
     *            �،���ЃR�[�h <BR>
     * @@param l_strSrvDiv -
     *            (�T�[�r�X�敪) <BR>
     *            �T�[�r�X�敪 <BR>
     * @@param l_strBranchCode -
     *            ���X�R�[�h
     * @@param l_strMainAccountCode -
     *            �ڋq�R�[�h
     * @@param l_tsCurrentTimestamp -
     *            ���ݓ��t
     * @@param l_strMarketCode
     *            �s��R�[�h
     * @@param l_strProductCode
     *            �����R�[�h
     * @@param l_strType
     *            �^�C�v
     * @@param l_digestKey - (�_�C�W�F�X�g�L�[)<BR>
     * �_�C�W�F�X�g�L�[
     * @@param l_strSSIDValue - (SSID�l)<BR>
     * SSID�l
     * @@return String
     * @@roseuid 41B8FB2F032B
     */
    public String createHashValue(
        String l_strInstitutionCode, 
        String l_strSrvDiv,
        String l_strBranchCode, 
        String l_strMainAccountCode, 
        Timestamp l_tsCurrentTimestamp,
        String l_strMarketCode, 
        String l_strProductCode, 
        String l_strType, 
        WEB3DigestKey l_digestKey,
        String l_strSSIDValue) throws WEB3BaseException;

    /**
     * (replace�n�b�V���v�Z����) <BR>
     * �����Ŏw�肳�ꂽ�n�b�V���v�Z�����敪���A <BR>
     * MessageDigest�I�u�W�F�N�g�������Ɏg�p���镶����ւƕϊ�����B <BR>
     * 
     * @@param l_strHashCalcMethodDiv -
     *            �n�b�V���v�Z�����敪
     * @@return String
     * @@roseuid 41B8FB2F0339
     */
    public String replaceHashCalHowTo(String l_strHashCalHowToDiv);

    /**
     * (get������t) <BR>
     * ����.���ݓ��t�̎��ԕ����𔻒肵�A <BR>
     * �n�b�V���v�Z�E�\���ϊ��̍ۂɁu���ݓ��t�v�Ƃ��ėp������t��ԋp����B <BR>
     * 
     * @@param l_tsCurrentTimestamp -
     *            ���ݓ��t
     * @@return java.util.Date
     * @@roseuid 41BE4FA703B1
     */
    public Date getControlTimestamp(Timestamp l_tsCurrentTimestamp);
    
    /**
     * �icreate�ڋqID�n�b�V���l�j<BR>
     * ����.�ڋq�R�[�h�A�،���ЃR�[�h�A���X�R�[�h����
     * �ڋq���擾���A�ڋq.�ڋqID������.�n�b�V���v�Z�����敪��
     * �n�b�V�������ĕԋp����B
     * 
     * @@param l_strHashCalHowTo
     *            �n�b�V���v�Z�����敪
     * @@param l_strInstitusionCode
     *            �،���ЃR�[�h
     * @@param l_strBranchCode
     *            ���X�R�[�h
     * @@param l_strAccountCode
     *            �ڋq�R�[�h
     * @@return String
     */
    public String createAccountCodeHashValue(String l_strHashCalHowTo, String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode)throws WEB3BaseException;
    
    /**
     * (get�Í����ۗL�������)<BR>
     * �����⏕��������A���M�ۗ̕L���������擾���A
     * �Í������ĕԋp����B
     *
     * @@param l_strInstitusionCode
     *            �،���ЃR�[�h
     * @@param l_strBranchCode
     *            ���X�R�[�h
     * @@param l_strAccountCode
     *            �ڋq�R�[�h
     * @@return String
     */
    public String getEncryptionMfAsset(String l_strInstitutionCode,
        String l_strBranchCode, String l_strAccountCode)throws WEB3BaseException;

    /**
     * (getCD�L�[)<BR>
     * CD�L�[�i���[�U�[���ʎq�j���쐬���ĕԋp����B<BR>
     * <BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCDKey(
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strSrvDiv,
        String l_strInstitutionCode) throws WEB3BaseException;
}@
