head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOtherOrgService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�O���A�g�T�[�r�X(WEB3SrvRegiOtherOrgService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 ���g �V�K�쐬 ���f��310,314,315,317,318,319,320
Revision History : 2008/02/26 ���g �d�l�ύX ���f��321
Revision History : 2008/03/03 ���g �d�l�ύX ���f��330
Revision History : 2008/03/19 ���g �d�l�ύX ���f��354,356
*/
package webbroker3.srvregi.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.message.WEB3SrvRegiSortKey;

/**
 * (�T�[�r�X���p�O���A�g�T�[�r�X)<BR>
 * �T�[�r�X���p�O���A�g�T�[�r�X�C���^�[�t�F�C�X�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public interface WEB3SrvRegiOtherOrgService extends Service
{

    /**
     * (get�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u�����A�f�[�^���擾����B<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        String l_strServiceDiv,
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (get�O���A�g���)<BR>
     * �����Ŏw�肳�ꂽ�ʔԁA�T�[�r�X�敪�ɊY������<BR>
     * �O���A�g���Ǘ��I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * is�s���b�N<BR>
     * true�F�s���b�N������B�@@false�F�s���b�N�����Ȃ��B<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getOtherOrgInfo(
        long l_lngSequenceNumber,
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (get�O���A�g���g�p����)<BR>
     * �O���A�g���Ǘ��e�[�u���̃X�e�[�^�X�����g�p�̃��R�[�h�̌�����ԋp<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@return Long
     * @@throws WEB3BaseException
     */
    public Long getOtherOrgUnUsedCount(
        String l_strServiceDiv) throws WEB3BaseException;


    /**
     * (get�O���A�g���ꗗ)<BR>
     * �w�肳�ꂽ�����ɍ��v����O���A�g���Ǘ�ð��ق��������A<BR>
     * ���̌��ʂ��O���A�g���Params�I�u�W�F�N�g�̔z��ɂ��ĕԋp����B<BR>
     * @@param l_strSequenceNumber - (�ʔ�)<BR>
     * �ʔ�<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_strID - (ID)<BR>
     * ID<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�ꗗ<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n���i���j)<BR>
     * �K�p�J�n���i���j<BR>
     * @@param l_tsAppliEndDate - (�K�p�J�n���i���j)<BR>
     * �K�p�J�n���i���j<BR>
     * @@param l_sortKeys - (�\�[�g����)<BR>
     * �Ώۍ���<BR>
     * ��Ɖ�̏ꍇ��<BR>
     * �@@�ʔԁ^ID�^�X�e�[�^�X�^���X�^�ڋq�^�K�p�J�n���^�K�p�I�����^�ŏI�X�V���^�ŏI�X�V��<BR>
     * ���޳�۰�ނ̏ꍇ��<BR>
     * �@@�ʔ�<BR>
     * @@return OtherOrgInfoAdminParams[]
     * @@throws WEB3BaseException
     */
    public OtherOrgInfoAdminParams[] getOtherOrgInfoList(
        String l_strSequenceNumber,
        String l_strServiceDiv,
        String l_strID,
        String l_strStatus,
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        WEB3SrvRegiSortKey[] l_sortKeys) throws WEB3BaseException;

    /**
     * (get���g�p�ʔԏ��)<BR>
     * �O���A�g���Ǘ��e�[�u���ɂ����āA�X�e�[�^�X�����g�p�̃��R�[�h�̒��ŁA<BR>
     * �ʔԂ��ŏ��̃��R�[�h�i�O���A�g���Ǘ��I�u�W�F�N�g�j��ԋp����B<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_blnIsRowLock - (is�s���b�N)<BR>
     * is�s���b�N<BR>
     * true�F�s���b�N������B�@@false�F�s���b�N�����Ȃ��B<BR>
     * @@return WEB3SrvRegiOtherOrgInfoAdmin
     * @@throws WEB3BaseException
     */
    public WEB3SrvRegiOtherOrgInfoAdmin getUnUseSequenceNumberInfo(
        String l_strServiceDiv,
        boolean l_blnIsRowLock) throws WEB3BaseException;

    /**
     * (submit�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u����UPDATE���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�T�[�r�X���p�jsubmit�O���A�g���v�Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_blnIsNewApplyDiv - (�V�K�\���敪)<BR>
     * �V�K�\���敪<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException;

    /**
     * (submit�O���A�g���)<BR>
     * �O���A�g���Ǘ��e�[�u����UPDATE���s���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strApplyLotteryDiv - (�\�����I�敪)<BR>
     * �\�����I�敪<BR>
     * @@param l_strServiceDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_tsAppliEndDate - (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     * @@param l_blnIsNewApplyDiv - (�V�K�\���敪)<BR>
     * �V�K�\���敪<BR>
     * @@throws WEB3BaseException
     */
    public void submitOtherOrgInfo(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strApplyLotteryDiv,
        String l_strServiceDiv,
        Timestamp l_tsAppliStartDate,
        Timestamp l_tsAppliEndDate,
        boolean l_blnIsNewApplyDiv) throws WEB3BaseException;
}
@
