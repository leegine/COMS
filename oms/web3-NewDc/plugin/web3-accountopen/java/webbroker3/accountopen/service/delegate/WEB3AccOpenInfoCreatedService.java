head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInfoCreatedService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݏ��쐬�T�[�r�X(WEB3AccOpenInfoCreatedService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �s�p (���u) �V�K�쐬
                 : 2006/06/08 ����(���u) �d�l�ύX�E���f��048�A050
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenInvalidValues;
import webbroker3.accountopen.WEB3AccOpenJudgeWaiting;
import webbroker3.accountopen.WEB3AccOpenMailAddressDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenTelNumberDuplicationCheck;
import webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo;
import webbroker3.accountopen.message.WEB3AccOpenVoucherInfo;
import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (�����J�ݏ��쐬�T�[�r�X)<BR>
 * �����J�ݏ��쐬�T�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */

public interface WEB3AccOpenInfoCreatedService extends Service 
{
    
    /**
     * (to�����J�ݐ\�����)<BR>
     * �����J�݌����q�I�u�W�F�N�g���A�����J�ݐ\�����𐶐�����B<BR>
     * <BR>
     * @@param l_accOpenExpAccountOpen - �����J�݌����q�I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInfo
     * @@roseuid 41AC4CAF0381
     */
    public WEB3AccOpenApplyInfo toAccOpenApplyInfo(WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen);
    
    /**
     * (to�����J�݌����q)<BR>
     * �����J�ݐ\�����I�u�W�F�N�g���A�����J�݌����q�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_accOpenApplyInfo - (�����J�ݐ\�����)<BR> 
     * �����J�ݐ\����񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g<BR>
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@throws WEB3BaseException
     * @@roseuid 41AC4CAF0391
     */
    public WEB3AccOpenExpAccountOpen toAccOpenExpAccountOpen(WEB3AccOpenApplyInfo l_accOpenApplyInfo) throws WEB3BaseException;
    
    /**
     * (to�`�[�쐬���)<BR>
     * �����J�ݓ`�[�쐬�X�e�[�^�X�I�u�W�F�N�g�̔z����A�`�[�쐬���𐶐�����B<BR>
     * <BR>
     * @@param l_accOpenVoucherCreatedStatuses - �����J�ݓ`�[�쐬�X�e�[�^�X�I�u�W�F�N�g�̔z��
     * @@return webbroker3.accountopen.message.WEB3AccOpenVoucherInfo
     * @@roseuid 41AC4CAF03B0
     */
    public WEB3AccOpenVoucherInfo toAccOpenVoucherInfo(WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses);
    
    /**
     * (to�s�����ڏ��)<BR>
     * �����J�ݕs���I�u�W�F�N�g���A�s�����ڏ��̔z��𐶐�����B<BR>
     * <BR>
     * @@param l_accOpenInvalidValues - �����J�ݕs���I�u�W�F�N�g
     * @@return webbroker3.accountopen.message.WEB3AccOpenInvalidItemInfo[]
     * @@roseuid 41AC4CAF03C0
     */
    public WEB3AccOpenInvalidItemInfo[] toAccOpenInvalidItemInfo(WEB3AccOpenInvalidValues l_accOpenInvalidValues);
    
    /**
     * (to�����J�ݕs��)<BR>
     * �s�����ڏ��̔z����A�����J�݃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_accOpenInvalidItemInfo - �s�����ڏ�񃁃b�Z�[�W�f�[�^
     * @@return webbroker3.accountopen.WEB3AccOpenInvalidValues
     * @@throws WEB3BaseException
     * @@roseuid 41AC4CAF03CF
     */
    public WEB3AccOpenInvalidValues toAccOpenInvalidValues(String l_strInstitutionCode, String l_strRequestNumber, WEB3AccOpenInvalidItemInfo[] l_accOpenInvalidItemInfo) throws WEB3BaseException;
    
    /**
     * (to�񕨗���)<BR>
     * ���b�Z�[�W�I�u�W�F�N�g�̍��ږ����A�Ή���������J�݌����q�e�[�u����<BR>
     * �񕨗����ɕϊ�����B<BR>
     * @@param l_strMessageItemName - ���b�Z�[�W���ڕ�����<BR>
     * ���@@�����J�ݐ\�����̊e�v���p�e�B������<BR>
     * ���@@Unit�N���X�̕ϐ��́A���ю�Ζ�����.��E���̂悤�ɁA<BR>
     * �u�ϐ���.���ږ��v�̌`���Ŏw�肷��B<BR>
     * @@return String
     * @@roseuid 41AC4CAF03DF
     */
    public String toColumnSymbolName(String l_strMessageItemName);
    
    /**
     * (to���b�Z�[�W���ږ�)<BR>
     * �����J�݌����q�e�[�u���̗񕨗�����Ή����郁�b�Z�[�W�I�u�W�F�N�g��<BR>
     * ���ږ��ɕϊ�����B<BR>
     * @@param l_strColumnName - �񕨗���<BR>
     * <BR>
     * ���@@�����J�݌����q�e�[�u���̗񕨗���<BR>
     * 
     * @@return String
     * @@roseuid 41AC4CB00016
     */
    public String toMessageItemName(String l_strColumnName);
    
    /**
     * (to�����J�ݐR���҂�)<BR>
     * �����J�ݐR���҂��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@return WEB3AccOpenJudgeWaiting
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenJudgeWaiting toAccOpenJudgeWaiting();
    
    /**
     * (to�����J�݃��[���A�h���X�d���`�F�b�N)<BR>
     * �����J�݃��[���A�h���X�d���`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return WEB3AccOpenMailAddressDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenMailAddressDuplicationCheck toAccOpenMailAddressDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode);
    
    /**
     * (to�����J�ݓd�b�ԍ��d���`�F�b�N)<BR>
     * �����J�ݓd�b�ԍ��d���`�F�b�N�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h�B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B<BR>
     * @@return WEB3AccOpenTelNumberDuplicationCheck
     * @@roseuid 41AC4CB00016
     */
    public WEB3AccOpenTelNumberDuplicationCheck toAccOpenTelNumberDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode);
      
}
@
